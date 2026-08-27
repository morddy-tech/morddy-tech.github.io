import { Github, Linkedin, Mail, Globe } from "lucide-react";
import { site } from "@/data/site";

const links = [
  { label: "GitHub", href: site.github, icon: Github },
  { label: "LinkedIn", href: site.socials.linkedin, icon: Linkedin },
  { label: "Email", href: site.socials.email, icon: Mail },
  { label: "Portfolio", href: site.url, icon: Globe },
];

export function SocialLinks({ className }: { className?: string }) {
  return (
    <ul className={`flex items-center gap-3 ${className ?? ""}`}>
      {links.map(({ label, href, icon: Icon }) => (
        <li key={label}>
          <a
            href={href}
            target="_blank"
            rel="noopener noreferrer"
            aria-label={label}
            className="flex h-10 w-10 items-center justify-center rounded-lg border border-line bg-surface text-muted transition-colors hover:border-accent hover:text-accent"
          >
            <Icon className="h-4.5 w-4.5" aria-hidden="true" />
          </a>
        </li>
      ))}
    </ul>
  );
}