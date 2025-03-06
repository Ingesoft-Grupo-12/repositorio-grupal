"use client";

import React from "react";
import Image from "next/image";
import DefaultAvatar from "@/assets/images/default-avatar.svg";

type UserCardProps = {
  id: string;
  userimage: string;
  username: string;
  email: string;
  role: string;
};

export default function UserCard({
  userimage,
  username,
  email,
}: UserCardProps) {
  return (
    <div className="flex w-full items-center justify-between p-4">
      <div className="flex items-center flex-grow min-w-0">
        <Image
          src={userimage || DefaultAvatar}
          width={48}
          height={48}
          alt={`${username}'s avatar`}
          className="rounded-full object-cover flex-shrink-0 overflow-hidden"
        />
        <div className="ml-4 flex-grow min-w-0">
          <div className="font-medium truncate">{username}</div>
          <div className="text-sm text-gray-400 truncate">{email}</div>
        </div>
      </div>
    </div>
  );
}
