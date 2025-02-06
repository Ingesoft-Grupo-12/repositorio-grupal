"use client";

import React from "react";
import Image from "next/image";

type FriendChatCardProps = {
  userId: number;
  userImage: string;
  userName: string;
  email: string;
  requestStatus: string;
};

export default function FriendChatCard({
  // userId,
  userImage,
  userName,
  email,
  // requestStatus,
}: FriendChatCardProps) {
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
        <div className="font-medium truncate">{userName}</div>
        <div className="text-sm text-gray-400 truncate">{email}</div>
      </div>
    </div>
  );
}
