import { Download, FileText } from "lucide-react";
import { site } from "@/data/site";
import { cn } from "@/lib/utils";

interface ResumeCTAProps {
  available: boolean;
  variant?: "solid" | "outline" | "ghost";
  className?: string;
}

export function ResumeCTA({ available, variant = "solid", className }: ResumeCTAProps) {
  if (!available) {
    return (
      <a
        href={site.socials.email}
        className={cn(
          "inline-flex items-center gap-2 rounded-lg px-5 py-2.5 text-sm font-semibold transition-colors",
          variant === "solid" && "bg-accent text-white hover:bg-accent-strong",
          variant === "outline" && "border border-line bg-surface text-foreground hover:border-accent hover:text-accent",
          variant === "ghost" && "text-foreground hover:text-accent",
          className
        )}
      >
        <FileText className="h-4 w-4" aria-hidden="true" />
        Request CV
      </a>
    );
  }

  return (
    <a
      href={site.cvPath}
      download="Ifedayo-Matthew-CV.pdf"
      className={cn(
        "inline-flex items-center gap-2 rounded-lg px-5 py-2.5 text-sm font-semibold transition-colors",
        variant === "solid" && "bg-accent text-white hover:bg-accent-strong",
        variant === "outline" && "border border-line bg-surface text-foreground hover:border-accent hover:text-accent",
        variant === "ghost" && "text-foreground hover:text-accent",
        className
      )}
    >
      <Download className="h-4 w-4" aria-hidden="true" />
      Download CV
    </a>
  );
}