import type { Metadata } from "next";
import { UserProvider } from "@auth0/nextjs-auth0/client";
import { Varela_Round } from "next/font/google";
import "./globals.css";

const varelaRound = Varela_Round({
  subsets: ["latin"],
  weight: "400",
  variable: "--font-varela-round",
});

export const metadata: Metadata = {
  title: "UNión",
  description:
    "Aplicación de mensajería instantánea, enfocada en el ámbito educativo.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <UserProvider>
        <body className={`${varelaRound.variable} antialiased`}>
          {children}
        </body>
      </UserProvider>
    </html>
  );
}
