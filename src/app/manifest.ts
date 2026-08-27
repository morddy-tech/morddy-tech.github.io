import type { MetadataRoute } from "next";
import { site } from "@/data/site";

export const dynamic = "force-static";

export default function manifest(): MetadataRoute.Manifest {
  return {
    name: `${site.name} — Full-Stack Software Developer & Cybersecurity-Focused Engineer`,
    short_name: site.brand,
    description: site.positioning,
    start_url: "/",
    display: "standalone",
    background_color: "#0a0f1c",
    theme_color: "#0a0f1c",
    icons: [
      {
        src: "/images/branding/favicon.png",
        sizes: "32x32",
        type: "image/png",
      },
      {
        src: "/images/branding/apple-touch-icon.png",
        sizes: "180x180",
        type: "image/png",
      },
    ],
  };
}