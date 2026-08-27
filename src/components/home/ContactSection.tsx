import { Mail, Phone, Github, MapPin } from "lucide-react";
import { site } from "@/data/site";
import { ContactForm } from "@/components/contact/ContactForm";
import { Reveal } from "@/components/shared/Reveal";
import { SectionHeading } from "@/components/shared/SectionHeading";

const channels = [
  {
    label: "Email",
    value: site.email,
    href: site.socials.email,
    icon: Mail,
  },
  {
    label: "Phone",
    value: site.phone,
    href: site.phoneHref,
    icon: Phone,
  },
  {
    label: "GitHub",
    value: `github.com/${site.githubUser}`,
    href: site.github,
    icon: Github,
  },
  {
    label: "Location",
    value: site.location,
    icon: MapPin,
  },
];

export function ContactSection() {
  return (
    <section id="contact" className="container-page py-20 sm:py-24">
      <SectionHeading
        eyebrow="// contact"
        title="Let's Build Something Secure."
        description="I'm open to software engineering, full-stack development, cybersecurity, and technology opportunities — remote, hybrid, or onsite."
      />
      <div className="grid gap-10 lg:grid-cols-[0.9fr_1.1fr]">
        <Reveal>
          <div className="flex h-full flex-col gap-5">
            {channels.map(({ label, value, href, icon: Icon }) => (
              <div
                key={label}
                className="tech-panel flex items-center gap-4 rounded-xl p-5"
              >
                <span className="flex h-11 w-11 shrink-0 items-center justify-center rounded-lg border border-accent/30 bg-accent/10 text-accent">
                  <Icon className="h-5 w-5" aria-hidden="true" />
                </span>
                <div className="min-w-0">
                  <p className="font-mono text-xs uppercase tracking-[0.18em] text-muted">{label}</p>
                  {href ? (
                    <a
                      href={href}
                      target={href.startsWith("http") ? "_blank" : undefined}
                      rel={href.startsWith("http") ? "noopener noreferrer" : undefined}
                      className="mt-1 block truncate text-sm font-semibold text-foreground transition-colors hover:text-accent"
                    >
                      {value}
                    </a>
                  ) : (
                    <p className="mt-1 truncate text-sm font-semibold text-foreground">{value}</p>
                  )}
                </div>
              </div>
            ))}
            <p className="rounded-xl border border-accent/30 bg-accent/5 p-4 text-sm leading-relaxed text-muted">
              For security-related matters, include the subject line{" "}
              <span className="font-mono text-accent">[Security]</span> so it reaches me first.
            </p>
          </div>
        </Reveal>

        <Reveal delay={100}>
          <div className="tech-panel rounded-xl p-6 sm:p-8">
            <h3 className="text-lg font-bold text-foreground">Send a message</h3>
            <p className="mt-1.5 mb-6 text-sm text-muted">
              Fill in the form and it will open a ready-to-send email — no data is stored by this site.
            </p>
            <ContactForm />
          </div>
        </Reveal>
      </div>
    </section>
  );
}