import { SectionHeading } from "@/components/shared/SectionHeading";
import { ProjectsExplorer } from "@/components/projects/ProjectsExplorer";
import { Reveal } from "@/components/shared/Reveal";
import { site } from "@/data/site";

export function ProjectsSection() {
  return (
    <section id="projects" className="container-page py-20 sm:py-24">
      <Reveal>
        <SectionHeading
          eyebrow="// featured projects"
          title="Evidence of engineering"
          description="Selected projects spanning AI products, full-stack platforms, and cybersecurity tooling. Every project links to its repository — built, not claimed."
        />
      </Reveal>
      <Reveal delay={80}>
        <ProjectsExplorer />
      </Reveal>
      <Reveal delay={120}>
        <p className="mt-12 text-center text-sm text-muted">
          More code and experiments live on{" "}
          <a
            href={site.github}
            target="_blank"
            rel="noopener noreferrer"
            className="font-semibold text-accent underline-offset-4 hover:underline"
          >
            github.com/{site.githubUser}
          </a>
          .
        </p>
      </Reveal>
    </section>
  );
}