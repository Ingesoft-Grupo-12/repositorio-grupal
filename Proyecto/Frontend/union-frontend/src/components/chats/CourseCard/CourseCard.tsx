"use client";

import { useState, useRef, useEffect } from "react";
import Image from "next/image";
import { format, isToday, isThisWeek } from "date-fns";
import { es } from "date-fns/locale";
import DefaultAvatar from "@/assets/images/default-avatar.svg";

type CourseCardProps = {
  courseId: number;
  courseCode: string;
  courseName: string;
  courseDescription: string;
  lastMessageSenderName: string;
  lastMessageContent: string;
  lastMessageTime: string;
};

export default function CourseCard({
  courseId,
  courseName,
  lastMessageSenderName,
  lastMessageContent,
  lastMessageTime,
}: CourseCardProps) {
  const lastMessageDate = new Date(lastMessageTime);
  const [menuOpen, setMenuOpen] = useState(false);
  const [menuPosition, setMenuPosition] = useState({ x: 0, y: 0 });
  const menuRef = useRef<HTMLDivElement>(null);

  let displayTime: string;
  if (isToday(lastMessageDate)) {
    displayTime = format(lastMessageDate, "hh:mm a", { locale: es });
  } else if (isThisWeek(lastMessageDate)) {
    displayTime = format(lastMessageDate, "EEEE", { locale: es });
  } else {
    displayTime = format(lastMessageDate, "dd/MM/yyyy", { locale: es });
  }

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

  const handleDeleteClick = async (event: React.MouseEvent) => {
    event.stopPropagation();
    try {
      const res = await fetch(`/api/courses/${courseId}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (!res.ok) throw new Error("Error al buscar cursos");
    } catch (error) {
      console.error("Error:", error);
    }
    setMenuOpen(false);
  };

  return (
    <div className="relative" onContextMenu={handleContextMenu}>
      <div className="flex items-center p-4 cursor-pointer hover:bg-gray-100">
        <Image
          src={DefaultAvatar}
          width={48}
          height={48}
          alt={`${courseName}'s avatar`}
          className="rounded-full object-cover flex-shrink-0"
        />
        <div className="ml-4 flex-grow min-w-0">
          <div className="font-medium truncate">{courseName}</div>
          <div className="text-sm text-gray-400 truncate">
            {"~" + lastMessageSenderName + ": " + lastMessageContent}
          </div>
        </div>
        <div className="text-xs text-gray-500 flex-shrink-0 whitespace-nowrap">
          {displayTime}
        </div>
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
            Eliminar curso
          </button>
        </div>
      )}
    </div>
  );
}
