"use client";

import React, { useState, useEffect, useRef } from "react";
import Image from "next/image";
import Link from "next/link";
import { useUser } from "@auth0/nextjs-auth0/client";
import { FaChevronDown, FaChevronUp } from "react-icons/fa";
import DefaultAvatar from "@/assets/images/default-avatar.svg";
import SkeletonAvatar from "../shared/SkeletonAvatar";

export default function Navbar() {
  const { user, isLoading } = useUser();
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const menuRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (!user) return;

    const registerUser = async () => {
      try {
        const newUser = {
          id: user.sub,
          username: user.name,
          email: user.email,
          role: "STUDENT",
        };

        const res = await fetch("/api/users", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(newUser),
        });

        if (!res.ok) throw new Error("Error al crear el usuario");

        const createdUser = await res.json();
        console.log("Usuario creado:", createdUser);
      } catch (error) {
        console.error("Error:", error);
      }
    };

    registerUser();
  }, [user]);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const handleClickOutside = (event: MouseEvent) => {
    if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
      setIsMenuOpen(false);
    }
  };

  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return (
    <nav>
      <div ref={menuRef} className="mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16 items-center pt-12">
          <div className="flex-shrink-0 ml-12">
            <Link href="/">
              <Image
                src="/logo-black.svg"
                alt="logo"
                width={60}
                height={60}
                style={{ width: 60, height: 60 }}
              />
            </Link>
          </div>

          <div className="flex space-x-4">
            <Link href="/chats" className="hover:text-gray-400">
              Mis chats
            </Link>
          </div>

          <div className="md:flex space-x-4 mr-12 relative">
            {isLoading ? (
              <SkeletonAvatar />
            ) : user ? (
              <div className="relative">
                <div
                  className="flex items-center space-x-2 cursor-pointer"
                  onClick={toggleMenu}
                >
                  <Image
                    src={user.picture || DefaultAvatar}
                    alt={user.name || "User avatar"}
                    width={40}
                    height={40}
                    className="rounded-full"
                  />
                  {isMenuOpen ? <FaChevronUp /> : <FaChevronDown />}
                </div>
                <div
                  className={`absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg z-10 transition-all duration-200 ease-in-out transform ${
                    isMenuOpen ? "opacity-100 scale-100" : "opacity-0 scale-95"
                  }`}
                >
                  <Link
                    href="/api/auth/logout"
                    className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  >
                    Log Out
                  </Link>
                </div>
              </div>
            ) : (
              <Link href="/api/auth/login" className="hover:text-gray-400">
                Log In
              </Link>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}
