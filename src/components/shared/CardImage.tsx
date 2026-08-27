import { Terminal } from "lucide-react";

interface CardImageProps {
  src?: string;
  alt?: string;
  title: string;
  category: string;
}

export function CardImage({ src, alt, title, category }: CardImageProps) {
  if (src) {
    return (
      <img
        src={src}
        alt={alt ?? `${title} preview`}
        loading="lazy"
        width={1200}
        height={675}
        className="h-full w-full object-cover object-top"
      />
    );
  }

  return (
    <div
      role="img"
      aria-label={`${title} — ${category}`}
      className="relative flex h-full w-full items-center justify-center overflow-hidden bg-grid-pattern"
    >
      <div className="absolute inset-0 bg-gradient-to-br from-accent/12 via-transparent to-transparent" aria-hidden="true" />
      <div className="relative flex flex-col items-center gap-3 px-6 text-center">
        <span className="flex h-12 w-12 items-center justify-center rounded-lg border border-accent/30 bg-surface text-accent">
          <Terminal className="h-5 w-5" aria-hidden="true" />
        </span>
        <span className="font-mono text-xs uppercase tracking-[0.2em] text-muted">{category}</span>
      </div>
    </div>
  );
}