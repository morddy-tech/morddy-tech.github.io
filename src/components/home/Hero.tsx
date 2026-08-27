import Link from "next/link";
import { ArrowRight, ShieldCheck } from "lucide-react";
import { site } from "@/data/site";
import { ResumeCTA } from "@/components/shared/ResumeCTA";

function AvailabilityBadge() {
  return (
    <p className="inline-flex items-center gap-2.5 rounded-full border border-line bg-surface px-4 py-1.5 text-sm text-foreground">
      <span className="marker-dot relative flex h-2 w-2" aria-hidden="true">
        <span className="h-2 w-2 rounded-full bg-accent" />
      </span>
      <span className="font-medium">{site.availability.label}</span>
      <span className="hidden text-muted sm:inline" aria-hidden="true">
        ·
      </span>
      <span className="hidden text-muted sm:inline">Remote / Hybrid / Onsite</span>
    </p>
  );
}

function TerminalPanel() {
  return (
    <div
      className="tech-panel overflow-hidden rounded-xl shadow-lg shadow-black/10"
      aria-hidden="true"
    >
      <div className="flex items-center gap-1.5 border-b border-line px-4 py-2.5">
        <span className="h-2.5 w-2.5 rounded-full bg-line" />
        <span className="h-2.5 w-2.5 rounded-full bg-line" />
        <span className="h-2.5 w-2.5 rounded-full bg-accent/60" />
        <span className="ml-2 font-mono text-xs text-muted">morddy — secure-engineering</span>
      </div>
      <div className="space-y-1.5 px-4 py-3.5 font-mono text-xs leading-relaxed">
        <p className="text-muted">
          <span className="text-accent">$</span> morddy --stack
        </p>
        <p className="text-foreground">
          next.js · react · typescript · node.js · postgresql · python
        </p>
        <p className="text-muted">
          <span className="text-accent">$</span> morddy --focus
        </p>
        <p className="text-foreground">
          secure-software · cloud-infrastructure · network-analysis
        </p>
        <p className="text-muted">
          <span className="text-accent">$</span> morddy --status
        </p>
        <p className="terminal-cursor text-accent">open-to-opportunities</p>
      </div>
    </div>
  );
}

export function Hero({ cvAvailable }: { cvAvailable: boolean }) {
  return (
    <section id="home" className="relative overflow-hidden pb-20 pt-32 sm:pt-36">
      <div className="bg-grid-pattern absolute inset-0" aria-hidden="true" />
      <div
        className="absolute -top-32 right-0 h-96 w-96 rounded-full bg-accent/10 blur-3xl"
        aria-hidden="true"
      />
      <div className="container-page relative">
        <div className="grid items-center gap-14 lg:grid-cols-[1.15fr_0.85fr]">
          <div className="hero-fade-up">
            <AvailabilityBadge />
            <h1 className="mt-6 text-4xl font-bold tracking-tight text-balance sm:text-5xl lg:text-6xl">
              {site.name.toUpperCase()}
            </h1>
            <p className="mt-4 text-2xl font-semibold tracking-tight text-foreground sm:text-3xl">
              {site.title}
            </p>
            <p className="mt-1.5 inline-flex items-center gap-2 font-mono text-sm text-accent sm:text-base">
              <ShieldCheck className="h-4 w-4" aria-hidden="true" />
              {site.secondaryTitle}
            </p>
            <p className="mt-6 max-w-xl text-base leading-relaxed text-muted sm:text-lg">
              {site.positioning}
            </p>
            <div className="mt-9 flex flex-wrap items-center gap-4">
              <Link
                href="/#projects"
                className="inline-flex items-center gap-2 rounded-lg bg-accent px-6 py-3 text-sm font-semibold text-white transition-colors hover:bg-accent-strong"
              >
                View Projects
                <ArrowRight className="h-4 w-4" aria-hidden="true" />
              </Link>
              <Link
                href="/#contact"
                className="inline-flex items-center gap-2 rounded-lg border border-line bg-surface px-6 py-3 text-sm font-semibold text-foreground transition-colors hover:border-accent hover:text-accent"
              >
                Let's Work Together
              </Link>
              <ResumeCTA variant="ghost" className="px-2" available={cvAvailable} />
            </div>
          </div>

          <div className="hero-fade-up hidden justify-center sm:flex" style={{ animationDelay: "120ms" }}>
            <div className="relative w-full max-w-sm">
              <div
                className="absolute -inset-3 rounded-2xl bg-gradient-to-br from-accent/25 via-transparent to-transparent blur-xl"
                aria-hidden="true"
              />
              <div className="relative overflow-hidden rounded-2xl border border-line bg-surface p-2 shadow-2xl shadow-black/20">
                <img
                  src="/images/profile/profile.jpg"
                  alt="Portrait of Ifedayo Matthew (Morddy), Full-Stack Software Developer"
                  width={1440}
                  height={2160}
                  className="aspect-[3/4] w-full rounded-xl object-cover object-top"
                />
              </div>
              <div className="relative mt-4">
                <TerminalPanel />
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}