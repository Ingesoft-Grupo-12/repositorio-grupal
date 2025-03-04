"use client";

import React from "react";
import Image from "next/image";

type UserCardProps = {
  userId: number;
  userImage: string;
  userName: string;
  userEmail: string;
  requestStatus: string;
};

export default function UserCard({
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  userId,
  userImage,
  userName,
  userEmail,
}: UserCardProps) {
  return (
    <div className="flex w-full items-center justify-between p-4">
      <div className="flex items-center flex-grow min-w-0">
        <Image
          src={userImage}
          width={48}
          height={48}
          alt={`${userName}'s avatar`}
          className="rounded-full object-cover flex-shrink-0"
        />
        <div className="ml-4 flex-grow min-w-0">
          <div className="font-medium truncate">{userName}</div>
          <div className="text-sm text-gray-400 truncate">{userEmail}</div>
        </div>
      </div>
    </div>
  );
}
