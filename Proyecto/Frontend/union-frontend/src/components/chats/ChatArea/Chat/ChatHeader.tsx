"use client";

import Image from "next/image";

type ChatHeaderProps = {
  userName: string;
  userImage: string;
};

export default function ChatHeader({ userName, userImage }: ChatHeaderProps) {
  return (
    <div className="flex items-center bg-white p-4 shado">
      <Image
        src={userImage}
        alt={userName || "User avatar"}
        width={40}
        height={40}
        className="rounded-full"
      />
      <div className="flex flex-col ml-4 truncate">
        <span className="text-lg">{userName}</span>
        <span className="text-sm text-green-500">escribiendo..</span>
      </div>
    </div>
  );
}
