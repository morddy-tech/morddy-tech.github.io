import { GraduationCap, Award } from "lucide-react";
import { education } from "@/data/education";
import { certifications } from "@/data/certifications";
import { Reveal } from "@/components/shared/Reveal";
import { SectionHeading } from "@/components/shared/SectionHeading";

export function Education() {
  return (
    <section id="education" className="border-y border-line bg-surface/60 py-20 sm:py-24">
      <div className="container-page">
        <SectionHeading
          eyebrow="// education & certifications"
          title="Formal training"
          description="Computer science education in progress, with verified training records alongside it."
        />
        <div className="grid gap-10 lg:grid-cols-2">
          <div>
            <h3 className="mb-5 inline-flex items-center gap-2 font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">
              <GraduationCap className="h-4 w-4" aria-hidden="true" />
              Education
            </h3>
            <ol className="space-y-5">
              {education.map((item, i) => (
                <Reveal as="li" key={item.degree} delay={i * 60}>
                  <div className="tech-panel rounded-xl p-5">
                    <div className="flex flex-wrap items-baseline justify-between gap-2">
                      <h4 className="text-base font-bold text-foreground">{item.degree}</h4>
                      <span className="font-mono text-xs text-accent">{item.period}</span>
                    </div>
                    <p className="mt-1 text-sm font-medium text-muted">{item.institution}</p>
                    {item.detail ? <p className="mt-2 text-sm leading-relaxed text-muted">{item.detail}</p> : null}
                  </div>
                </Reveal>
              ))}
            </ol>
          </div>

          <div>
            <h3 className="mb-5 inline-flex items-center gap-2 font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">
              <Award className="h-4 w-4" aria-hidden="true" />
              Certifications
            </h3>
            <ol className="space-y-5">
              {certifications.map((cert, i) => (
                <Reveal as="li" key={cert.name} delay={i * 60}>
                  <div className="tech-panel rounded-xl p-5">
                    <h4 className="text-base font-bold text-foreground">{cert.name}</h4>
                    {cert.provider ? (
                      <p className="mt-1 text-sm font-medium text-muted">{cert.provider}</p>
                    ) : null}
                    {cert.note ? <p className="mt-2 text-sm leading-relaxed text-muted">{cert.note}</p> : null}
                  </div>
                </Reveal>
              ))}
            </ol>
          </div>
        </div>
      </div>
    </section>
  );
}