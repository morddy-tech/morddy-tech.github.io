import { Briefcase } from "lucide-react";
import { experience } from "@/data/experience";
import { Reveal } from "@/components/shared/Reveal";
import { SectionHeading } from "@/components/shared/SectionHeading";

export function Experience() {
  return (
    <section id="experience" className="container-page py-20 sm:py-24">
      <SectionHeading
        eyebrow="// experience"
        title="Professional experience"
        description="Engineering-first work history — with earlier roles in administration, teaching, and design supporting communication, analysis, and visual systems."
      />
      <ol className="relative space-y-8 border-l border-line pl-6 sm:pl-8">
        {experience.map((role, i) => (
          <Reveal as="li" key={role.role} delay={i * 60} className="relative">
            <span
              className="absolute -left-[calc(1.5rem+5px)] top-1.5 h-2.5 w-2.5 rounded-full border-2 border-accent bg-background sm:-left-[calc(2rem+5px)]"
              aria-hidden="true"
            />
            <div className="flex flex-wrap items-baseline gap-x-3 gap-y-1">
              <h3 className="text-lg font-bold text-foreground">{role.role}</h3>
              <span className="font-mono text-sm text-accent">{role.organization}</span>
              {role.period ? (
                <span className="rounded-md border border-line bg-surface-2 px-2 py-0.5 font-mono text-xs text-muted">
                  {role.period}
                </span>
              ) : null}
            </div>
            <p className="mt-2 max-w-2xl text-sm leading-relaxed text-muted">{role.summary}</p>
            <ul className="mt-3 flex flex-wrap gap-1.5">
              {role.focus.map((item) => (
                <li
                  key={item}
                  className="inline-flex items-center gap-1 rounded-md border border-line bg-surface px-2 py-1 font-mono text-[0.7rem] text-muted"
                >
                  <Briefcase className="h-3 w-3 text-accent" aria-hidden="true" />
                  {item}
                </li>
              ))}
            </ul>
          </Reveal>
        ))}
      </ol>
    </section>
  );
}