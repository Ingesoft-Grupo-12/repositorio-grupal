"use client";

import { useState, useRef, useEffect } from "react";
import React from "react";
import Image from "next/image";

type FriendChatCardProps = {
  userId: number;
  userImage: string;
  userName: string;
  email: string;
};

export default function FriendChatCard({
  userId,
  userImage,
  userName,
  email,
}: FriendChatCardProps) {
  const [menuOpen, setMenuOpen] = useState(false);
  const [menuPosition, setMenuPosition] = useState({ x: 0, y: 0 });
  const menuRef = useRef<HTMLDivElement>(null);

  const handleContextMenu = (event: React.MouseEvent) => {
    event.preventDefault();
    setMenuPosition({ x: event.clientX, y: event.clientY });
    setMenuOpen(true);
  };

  useEffect(() => {
    function handleClickOutside(event: MouseEvent) {
      if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
        setMenuOpen(false);
      }
    }
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  const handleDeleteClick = (event: React.MouseEvent) => {
    event.stopPropagation();
    console.log("API-Eliminar Amigo", userId);
    setMenuOpen(false);
  };

  return (
    <div
      className="flex items-center p-4 cursor-pointer "
      onContextMenu={handleContextMenu}
    >
      <Image
        src={userImage}
        width={48}
        height={48}
        alt={`${userName}'s avatar`}
        className="rounded-full object-cover flex-shrink-0"
      />
      <div className="ml-4 flex-grow min-w-0">
        <div className="font-medium truncate">{userName}</div>
        <div className="text-sm text-gray-400 truncate">{email}</div>
      </div>
      {menuOpen && (
        <div
          ref={menuRef}
          className="fixed bg-white shadow-lg rounded-md p-2 z-50 border"
          style={{ top: menuPosition.y, left: menuPosition.x }}
        >
          <button
            className="text-red-500 px-4 py-2 w-full text-left hover:bg-gray-100"
            onClick={handleDeleteClick}
          >
            Eliminar amigo
          </button>
        </div>
      )}
    </div>
  );
}
