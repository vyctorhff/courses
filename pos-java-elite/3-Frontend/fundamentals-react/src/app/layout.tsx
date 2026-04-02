import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Fundamentals React + Next.JS",
  description: "Java Elite course: fundamentals for react",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-br">
      <body>
        {children}
      </body>
    </html>
  );
}
