import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  output: "export",
  trailingSlash: true,
  images: {
    unoptimized: true,
  },
  reactStrictMode: true,
  // Lint runs explicitly via `npm run lint` in CI (flat config detection is
  // a known Next 15 cosmetic warning with flat configs)
  eslint: {
    ignoreDuringBuilds: true,
  },
};

export default nextConfig;