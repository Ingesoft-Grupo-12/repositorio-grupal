"use client";

import React from "react";
import Image from "next/image";
import { format, isToday, isThisWeek } from "date-fns";
import { es } from "date-fns/locale";

type ChatCardProps = {
  userId: number;
  userImage: string;
  userName: string;
  lastMessage: string;
  lastMessageTime: string;
};

export default function ChatCard({
  // userId,
  userImage,
  userName,
  lastMessage,
  lastMessageTime,
}: ChatCardProps) {
  const lastMessageDate = new Date(lastMessageTime);

  let displayTime: string;
  if (isToday(lastMessageDate)) {
    displayTime = format(lastMessageDate, "hh:mm a", { locale: es });
  } else if (isThisWeek(lastMessageDate)) {
    displayTime = format(lastMessageDate, "EEEE", { locale: es });
  } else {
    displayTime = format(lastMessageDate, "dd/MM/yyyy", { locale: es });
  }
  return (
    <div className="flex items-center p-4 cursor-pointer">
      <Image
        src={userImage}
        width={48}
        height={48}
        alt={`${userName}'s avatar`}
        className="rounded-full object-cover flex-shrink-0"
      />
      <div className="ml-4 flex-grow min-w-0">
        <div className=" font-medium truncate">{userName}</div>
        <div className="text-sm text-gray-400 truncate">{lastMessage}</div>
      </div>
      <div className="text-xs text-gray-500 flex-shrink-0 whitespace-nowrap">
        {displayTime}
      </div>
    </div>
  );
}
