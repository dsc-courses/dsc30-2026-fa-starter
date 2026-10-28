"""Plot one or more harness CSVs, averaging measured trials for each series."""
import argparse
from pathlib import Path
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt
import pandas as pd

def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("csv", nargs="+", help="CSV files from RuntimeAnalysis")
    parser.add_argument("--output-dir", default="plots")
    parser.add_argument("--title", default="Measured sorting runtime")
    args = parser.parse_args()
    data = pd.concat([pd.read_csv(path) for path in args.csv], ignore_index=True)
    required = {"sort_type", "input_order", "pivot", "data_size", "trial", "run_time_ns"}
    if not required.issubset(data.columns) or data.empty:
        parser.error("Input must contain nonempty RuntimeAnalysis CSV data")
    out = Path(args.output_dir)
    out.mkdir(parents=True, exist_ok=True)
    for sort, rows in data.groupby("sort_type", sort=False):
        fig, ax = plt.subplots(figsize=(7, 4.5))
        rows = rows.copy()
        def series_name(row):
            if "series_label" in rows.columns and pd.notna(row["series_label"]):
                return str(row["series_label"])
            label = str(row["input_order"])
            if sort == "quick": label += " / " + str(row["pivot"]) + " pivot"
            return label
        rows["display_series"] = rows.apply(series_name, axis=1)
        for label, series in rows.groupby("display_series", sort=False):
            means = series.groupby("data_size")["run_time_ns"].mean().sort_index() / 1_000_000
            ax.scatter(means.index, means.values, label=label)
        ax.set(title=args.title + " — " + sort.title() + " Sort",
               xlabel="Input size (elements)", ylabel="Mean sorting time (ms)")
        ax.legend()
        ax.grid(alpha=0.25)
        fig.tight_layout()
        target = out / (sort + ".png")
        if target.exists():
            plt.close(fig)
            parser.error("Refusing to overwrite " + str(target))
        fig.savefig(target, dpi=180)
        plt.close(fig)
        print(target)

if __name__ == "__main__":
    main()
