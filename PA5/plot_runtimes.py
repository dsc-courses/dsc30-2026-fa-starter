"""Choose TASK below and click Run, or optionally supply harness CSV arguments."""
import argparse
from pathlib import Path

import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt
import pandas as pd

# STUDENT SETTING: choose 1, 2, 3, 4, 5 or 6, then click Run.
TASK = 1
# Java writes relative to its working directory. Keep its CSVs beside this script.
DATA_DIR = Path(__file__).resolve().parent

# (algorithm, filename, input_order, pivot, legend label)
TASK_SERIES = {
    1: [(sort, "task1.csv", "random", "first" if sort == "quick" else "n/a", "Random")
        for sort in ("insertion", "selection", "quick", "merge")],
    2: [("insertion", "task1.csv", "random", "n/a", "Random"),
        ("insertion", "task2.csv", "favorable", "n/a", "Favorable")],
    3: [("quick", "task1.csv", "random", "first", "Random / first pivot"),
        ("quick", "task3.csv", "unfavorable", "first", "Unfavorable / first pivot")],
    4: [("quick", "task3.csv", "unfavorable", "first", "Unfavorable / first pivot"),
        ("quick", "task4.csv", "unfavorable", "middle", "Unfavorable / middle pivot")],
    5: [("selection", "task1.csv", "random", "n/a", "Random"),
        ("selection", "task5_ascending.csv", "ascending", "n/a", "Ascending"),
        ("selection", "task5_descending.csv", "descending", "n/a", "Descending")],
    6: [("merge", "task1.csv", "random", "n/a", "Random"),
        ("merge", "task6_ascending.csv", "ascending", "n/a", "Ascending"),
        ("merge", "task6_descending.csv", "descending", "n/a", "Descending")],
}
TITLES = {
    1: "Task 1: Random inputs", 2: "Task 2: Favorable insertion-sort inputs",
    3: "Task 3: Unfavorable quick-sort inputs", 4: "Task 4: Quick-sort pivot comparison",
    5: "Task 5: Selection-sort input orders", 6: "Task 6: Merge-sort input orders",
}
REQUIRED = {"sort_type", "input_order", "pivot", "data_size", "trial", "run_time_ns"}


def read_results(path):
    if not path.is_file():
        raise ValueError(f"Missing results: {path}. Run the corresponding Java experiment first. "
                         "Keep the CSV beside plot_runtimes.py (or change DATA_DIR).")
    # Preserve the harness's literal "n/a" pivot label for non-quick sorts.
    data = pd.read_csv(path, keep_default_na=False)
    if data.empty or not REQUIRED.issubset(data.columns):
        raise ValueError(f"{path.name}: expected nonempty RuntimeAnalysis CSV data.")
    for column in ("data_size", "trial", "run_time_ns"):
        data[column] = pd.to_numeric(data[column], errors="raise")
        if data[column].isna().any():
            raise ValueError(f"{path.name}: missing values in {column}.")
    if (data["data_size"] <= 0).any() or (data["run_time_ns"] < 0).any():
        raise ValueError(f"{path.name}: sizes must be positive and times nonnegative.")
    return data


def load_task(task):
    if task not in TASK_SERIES:
        raise ValueError("TASK must be an integer from 1 through 6.")
    cache = {}
    frames = []
    for sort, filename, order, pivot, label in TASK_SERIES[task]:
        if filename not in cache:
            cache[filename] = read_results(DATA_DIR / filename)
        data = cache[filename]
        rows = data[(data["sort_type"] == sort) & (data["input_order"] == order)
                    & (data["pivot"] == pivot)].copy()
        if rows.empty:
            raise ValueError(f"{filename}: no {sort} results labeled {order}, pivot={pivot}. "
                             "Check the Java EXPERIMENT setting and the saved CSV.")
        rows["display_series"] = label
        frames.append(rows)
    return pd.concat(frames, ignore_index=True)


def validate_comparisons(data):
    for sort, rows in data.groupby("sort_type", sort=False):
        reference_sizes = None
        reference_counts = None
        for label, series in rows.groupby("display_series", sort=False):
            sizes = set(series["data_size"])
            counts = series.groupby("data_size").size().sort_index()
            if reference_sizes is None:
                reference_sizes, reference_counts = sizes, counts
            elif sizes != reference_sizes:
                raise ValueError(f"{sort}: series use different input sizes. Collect matching sizes "
                                 "for both/all series; archive old CSVs before rerunning. "
                                 "For a smaller baseline, restore the random generator and run task1 "
                                 "with matching INPUT_SIZES. Keep earlier plots for your report.")
            elif not counts.equals(reference_counts):
                raise ValueError(f"{sort}: series use different trial counts. Use matching TRIALS "
                                 "and collect complete results for every input size.")


def save_plots(data, output_dir, title):
    # Validate every comparison before creating any plots.
    validate_comparisons(data)
    output_dir.mkdir(parents=True, exist_ok=True)
    for sort, rows in data.groupby("sort_type", sort=False):
        fig, ax = plt.subplots(figsize=(7, 4.5))
        for label, series in rows.groupby("display_series", sort=False):
            means = series.groupby("data_size")["run_time_ns"].mean().sort_index() / 1_000_000
            ax.scatter(means.index, means.values, label=label)
        ax.set(title=title + " — " + str(sort).title() + " Sort",
               xlabel="Input size (elements)", ylabel="Mean sorting time (ms)")
        ax.legend()
        ax.grid(alpha=0.25)
        fig.tight_layout()
        target = output_dir / (str(sort) + ".png")
        fig.savefig(target, dpi=180)
        plt.close(fig)
        print(f"Saved {target.resolve()}")


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("csv", nargs="*", help="Optional CSV files; omit to use TASK")
    parser.add_argument("--output-dir")
    parser.add_argument("--title")
    args = parser.parse_args()
    try:
        if args.csv:
            data = pd.concat([read_results(Path(path)) for path in args.csv], ignore_index=True)
            def series_name(row):
                if "series_label" in data.columns and pd.notna(row["series_label"]) and str(row["series_label"]).strip():
                    return str(row["series_label"])
                label = str(row["input_order"])
                if row["sort_type"] == "quick":
                    label += " / " + str(row["pivot"]) + " pivot"
                return label
            data["display_series"] = data.apply(series_name, axis=1)
            output = Path(args.output_dir) if args.output_dir else DATA_DIR / "plots" / "custom"
            title = args.title or "Measured sorting runtime"
        else:
            data = load_task(TASK)
            output = Path(args.output_dir) if args.output_dir else DATA_DIR / "plots" / f"task{TASK}"
            title = args.title or TITLES[TASK]
        save_plots(data, output, title)
    except (ValueError, OSError, pd.errors.ParserError) as error:
        parser.exit(1, f"Cannot create plots: {error}\n")


if __name__ == "__main__":
    main()
