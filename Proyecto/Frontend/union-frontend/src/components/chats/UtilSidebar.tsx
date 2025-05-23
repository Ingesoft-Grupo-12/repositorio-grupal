"use client";
import Image from "next/image";
import Link from "next/link";
import { FaCarSide, FaCog, FaUserFriends } from "react-icons/fa";
import { LuMessageSquareMore } from "react-icons/lu";
import { useUser } from "@auth0/nextjs-auth0/client";
import DefaultAvatar from "@/assets/images/default-avatar.svg";
import SkeletonAvatar from "@/components/shared/SkeletonAvatar";
import { ModuleType } from "@/app/chats/chatsTypings";

interface UtilSidebarProps {
  selectedModule: ModuleType;
  setSelectedModule: (option: ModuleType) => void;
}

type IconType = React.ComponentType<{ size?: number; className?: string }>;

const menuItems: { module: ModuleType; icon: IconType }[] = [
  { module: "messages", icon: LuMessageSquareMore },
  { module: "friends", icon: FaUserFriends },
  { module: "transport", icon: FaCarSide },
];

export default function UtilSidebar({
  selectedModule,
  setSelectedModule,
}: UtilSidebarProps) {
  const { user } = useUser();

  return (
    <div className="flex-grow flex flex-col justify-between min-w-20 w-full max-w-24 bg-white p-4 rounded-md overflow-y-auto">
      <div className="flex flex-col items-center mt-4">
        <Link href="/">
          <Image src="/logo-black.svg" alt="logo" width={45} height={45} />
        </Link>
        <div className="mt-10">
          {menuItems.map(({ module, icon: Icon }) => (
            <div
              key={module}
              className={`group flex items-center justify-center w-10 h-10 mb-6 rounded-full transition cursor-pointer ${
                selectedModule === module ? "bg-blue-500" : "hover:bg-gray-100"
              }`}
              onClick={() => setSelectedModule(module)}
            >
              <Icon
                size={24}
                className={`${
                  selectedModule === module
                    ? "text-white"
                    : "text-gray-600 group-hover:text-gray-900"
                }`}
              />
            </div>
          ))}
        </div>
      </div>
      <div className="flex flex-col items-center">
        <div className="mb-4">
          <div className="group flex items-center justify-center w-10 h-10 rounded-full hover:bg-gray-100 transition cursor-pointer">
            <FaCog
              size={22}
              className="text-gray-600 group-hover:text-gray-900"
            />
          </div>
        </div>
        {user ? (
          <Image
            src={user.picture || DefaultAvatar}
            alt={user.name || "User avatar"}
            width={40}
            height={40}
            className="rounded-full"
          />
        ) : (
          <SkeletonAvatar />
        )}
      </div>
    </div>
  );
}
