import Link from "next/link";
import { ArrowRight, ShieldCheck } from "lucide-react";
import { securityFocus, securityWorkflow, securityStatement } from "@/data/security";
import { getProject } from "@/data/projects";
import { Reveal } from "@/components/shared/Reveal";
import { SectionHeading } from "@/components/shared/SectionHeading";

export function SecuritySection() {
  const primaryProject = getProject("network-traffic-analyzer");

  return (
    <section id="security" className="border-y border-line bg-surface/60 py-20 sm:py-24">
      <div className="container-page">
        <SectionHeading
          eyebrow="// security & cybersecurity"
          title="A specialization, not a side interest"
          description="Security is engineered into the applications I build — and it is also a domain I work in directly: monitoring, networks, and detection."
        />

        <Reveal>
          <blockquote className="mx-auto max-w-3xl text-center text-lg font-medium leading-relaxed text-foreground sm:text-xl">
            "{securityStatement}"
          </blockquote>
        </Reveal>

        <Reveal delay={80}>
          <ul className="mt-10 flex flex-wrap justify-center gap-2">
            {securityFocus.map((focus) => (
              <li
                key={focus}
                className="inline-flex items-center gap-1.5 rounded-lg border border-accent/30 bg-accent/5 px-3.5 py-1.5 font-mono text-xs text-foreground"
              >
                <ShieldCheck className="h-3.5 w-3.5 text-accent" aria-hidden="true" />
                {focus}
              </li>
            ))}
          </ul>
        </Reveal>

        <Reveal delay={120}>
          <div className="mt-14 overflow-x-auto pb-2">
            <ol
              className="flex min-w-max items-stretch gap-3"
              aria-label="Security workflow from application to detection"
            >
              {securityWorkflow.map((item, i) => (
                <li key={item.step} className="flex items-center gap-3">
                  <div className="tech-panel w-44 rounded-xl p-4">
                    <p className="font-mono text-xs uppercase tracking-[0.18em] text-accent">
                      {String(i + 1).padStart(2, "0")}
                    </p>
                    <h3 className="mt-1.5 text-sm font-bold text-foreground">{item.step}</h3>
                    <p className="mt-1.5 text-xs leading-relaxed text-muted">{item.detail}</p>
                  </div>
                  {i < securityWorkflow.length - 1 ? (
                    <ArrowRight className="h-4 w-4 shrink-0 text-accent" aria-hidden="true" />
                  ) : null}
                </li>
              ))}
            </ol>
          </div>
        </Reveal>

        {primaryProject ? (
          <Reveal delay={140}>
            <div className="mx-auto mt-14 flex max-w-2xl flex-col items-center gap-4 rounded-xl border border-line bg-surface p-6 text-center sm:flex-row sm:text-left">
              <span className="flex h-12 w-12 shrink-0 items-center justify-center rounded-lg bg-accent/10 text-accent">
                <ShieldCheck className="h-6 w-6" aria-hidden="true" />
              </span>
              <div className="flex-1">
                <p className="font-mono text-xs uppercase tracking-[0.18em] text-accent">Primary security project</p>
                <h3 className="mt-1 text-lg font-bold text-foreground">{primaryProject.title}</h3>
                <p className="mt-1 text-sm text-muted">{primaryProject.shortDescription}</p>
              </div>
              <Link
                href={`/projects/${primaryProject.slug}/`}
                className="inline-flex shrink-0 items-center gap-2 rounded-lg border border-accent/40 px-4 py-2.5 text-sm font-semibold text-accent transition-colors hover:bg-accent hover:text-white"
              >
                View project
                <ArrowRight className="h-4 w-4" aria-hidden="true" />
              </Link>
            </div>
          </Reveal>
        ) : null}
      </div>
    </section>
  );
}