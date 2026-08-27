import { skillCategories } from "@/data/skills";
import { Reveal } from "@/components/shared/Reveal";
import { SectionHeading } from "@/components/shared/SectionHeading";
import { TechBadge } from "@/components/shared/TechBadge";

export function TechStack() {
  return (
    <section id="skills" className="border-y border-line bg-surface/60 py-20 sm:py-24">
      <div className="container-page">
        <SectionHeading
          eyebrow="// technical stack"
          title="Tools I build with"
          description="Primary technologies I reach for first, organized by discipline. The stack emphasizes typed full-stack development, relational data, and security tooling."
        />
        <div className="grid gap-6 md:grid-cols-2">
          {skillCategories.map((category, i) => (
            <Reveal key={category.label} delay={(i % 2) * 90}>
              <div className="tech-panel h-full rounded-xl p-6">
                <div className="flex items-baseline justify-between gap-4">
                  <h3 className="text-base font-semibold text-foreground">{category.label}</h3>
                  <span className="font-mono text-[0.65rem] uppercase tracking-[0.18em] text-muted">
                    {String(i + 1).padStart(2, "0")}
                  </span>
                </div>
                <p className="mt-1.5 text-sm text-muted">{category.description}</p>
                <ul className="mt-5 flex flex-wrap gap-2">
                  {category.skills.map((skill) => (
                    <li key={skill.name}>
                      <TechBadge label={skill.name} primary={skill.primary} />
                    </li>
                  ))}
                </ul>
              </div>
            </Reveal>
          ))}
        </div>
      </div>
    </section>
  );
}