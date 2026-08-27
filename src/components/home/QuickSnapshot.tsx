import { Code2, ShieldCheck, GraduationCap, MapPin } from "lucide-react";
import { Reveal } from "@/components/shared/Reveal";

const metrics = [
  {
    label: "Full-Stack Development",
    detail: "Frontend, backend, APIs, and databases",
    icon: Code2,
  },
  {
    label: "Cybersecurity",
    detail: "Monitoring, networks, and secure engineering",
    icon: ShieldCheck,
  },
  {
    label: "Computer Science",
    detail: "BSc in progress — algorithms & systems",
    icon: GraduationCap,
  },
  {
    label: "Open to Relocation",
    detail: "Remote · Hybrid · Onsite",
    icon: MapPin,
  },
];

export function QuickSnapshot() {
  return (
    <section aria-label="Professional snapshot" className="border-y border-line bg-surface/60">
      <div className="container-page grid gap-px overflow-hidden sm:grid-cols-2 lg:grid-cols-4">
        {metrics.map(({ label, detail, icon: Icon }, i) => (
          <Reveal key={label} delay={i * 70} className="flex items-start gap-4 px-2 py-7 sm:px-6">
            <span className="flex h-10 w-10 shrink-0 items-center justify-center rounded-lg border border-line bg-surface-2 text-accent">
              <Icon className="h-4.5 w-4.5" aria-hidden="true" />
            </span>
            <div>
              <h3 className="text-sm font-semibold text-foreground">{label}</h3>
              <p className="mt-1 text-xs leading-relaxed text-muted">{detail}</p>
            </div>
          </Reveal>
        ))}
      </div>
    </section>
  );
}