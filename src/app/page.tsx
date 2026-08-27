import { Hero } from "@/components/home/Hero";
import { QuickSnapshot } from "@/components/home/QuickSnapshot";
import { About } from "@/components/home/About";
import { TechStack } from "@/components/home/TechStack";
import { ProjectsSection } from "@/components/home/ProjectsSection";
import { SecuritySection } from "@/components/home/SecuritySection";
import { Experience } from "@/components/home/Experience";
import { Education } from "@/components/home/Education";
import { ContactSection } from "@/components/home/ContactSection";
import { cvFileExists } from "@/lib/cv";

export default function HomePage() {
  const cvAvailable = cvFileExists();
  return (
    <>
      <Hero cvAvailable={cvAvailable} />
      <QuickSnapshot />
      <About />
      <TechStack />
      <ProjectsSection />
      <SecuritySection />
      <Experience />
      <Education />
      <ContactSection />
    </>
  );
}