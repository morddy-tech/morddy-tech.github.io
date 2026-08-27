import Link from "next/link";
import { site } from "@/data/site";
import { SocialLinks } from "@/components/shared/SocialLinks";

export function Footer() {
  const year = new Date().getFullYear();
  return (
    <footer className="border-t border-line bg-surface">
      <div className="container-page flex flex-col items-center gap-8 py-12 text-center sm:flex-row sm:justify-between sm:text-left">
        <div>
          <p className="font-mono text-sm font-bold tracking-[0.18em] text-foreground">
            {site.name} <span className="text-muted">/ {site.brand}</span>
          </p>
          <p className="mt-2 text-sm text-muted">
            {site.title} | {site.secondaryTitle}
          </p>
        </div>

        <SocialLinks />

        <div className="flex flex-col items-center gap-1 sm:items-end">
          <Link href="/" className="text-sm text-muted transition-colors hover:text-accent">
            morddy-tech.github.io
          </Link>
          <p className="text-sm text-muted">© {year} {site.name}. All rights reserved.</p>
        </div>
      </div>
    </footer>
  );
}