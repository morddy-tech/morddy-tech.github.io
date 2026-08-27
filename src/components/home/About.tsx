import { CheckCircle2 } from "lucide-react";
import { Reveal } from "@/components/shared/Reveal";
import { SectionHeading } from "@/components/shared/SectionHeading";

const domains = [
  "Frontend",
  "Backend",
  "Databases",
  "APIs",
  "Authentication",
  "Cloud",
  "Linux",
  "Cybersecurity",
];

export function About() {
  return (
    <section id="about" className="container-page py-20 sm:py-24">
      <SectionHeading
        eyebrow="// about"
        title="Engineering with security in mind"
        description="A Computer Science professional and full-stack developer who builds modern web applications, AI-powered products, backend systems, and cybersecurity tools."
      />
      <div className="grid gap-10 lg:grid-cols-2">
        <Reveal>
          <div className="space-y-5 text-base leading-relaxed text-muted">
            <p>
              I build across the entire stack — <strong className="font-semibold text-foreground">frontend, backend,
              databases, APIs, authentication, cloud, and Linux</strong> — and I am particularly interested in the
              intersection between software engineering and security.
            </p>
            <p>
              That interest shapes how I work: applications aren't finished when they work, they're finished when I
              understand how they hold up — how identities are verified, how data is protected, how systems are
              monitored, and how attacks are detected.
            </p>
            <p>
              I am currently pursuing a BSc in Computer Science at the University of the People while building
              production-oriented products. What drives me is what I can build and contribute — real systems that
              solve real problems.
            </p>
          </div>
        </Reveal>
        <Reveal delay={100}>
          <div className="tech-panel rounded-xl p-6 sm:p-8">
            <h3 className="font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">What I work across</h3>
            <ul className="mt-5 grid grid-cols-2 gap-x-4 gap-y-3">
              {domains.map((domain) => (
                <li key={domain} className="flex items-center gap-2.5 text-sm font-medium text-foreground">
                  <CheckCircle2 className="h-4 w-4 shrink-0 text-accent" aria-hidden="true" />
                  {domain}
                </li>
              ))}
            </ul>
            <p className="mt-6 border-t border-line pt-5 text-sm leading-relaxed text-muted">
              Design experience from client work feeds directly into UI decisions — interfaces are not decorations,
              they are part of the product's security and usability surface.
            </p>
          </div>
        </Reveal>
      </div>
    </section>
  );
}