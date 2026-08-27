import { cn } from "@/lib/utils";

interface TechBadgeProps {
  label: string;
  primary?: boolean;
}

export function TechBadge({ label, primary }: TechBadgeProps) {
  return (
    <span
      className={cn(
        "inline-flex items-center rounded-md border px-2.5 py-1 font-mono text-xs",
        primary
          ? "border-accent/40 bg-accent/10 text-accent"
          : "border-line bg-surface-2 text-muted"
      )}
    >
      {label}
    </span>
  );
}