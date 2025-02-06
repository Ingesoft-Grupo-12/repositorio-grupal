import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json([
    {
      userId: 1,
      userImage: "https://picsum.photos/id/100/48",
      userName: "John Doe",
      lastMessage: "Hello! How are you?",
      lastMessageTime: "2025-01-26T14:00:00Z",
    },
    {
      userId: 2,
      userImage: "https://picsum.photos/id/38/48",
      userName: "Jane Smith",
      lastMessage: "Let's catch up later!",
      lastMessageTime: "2025-01-25T18:30:00Z",
    },
    {
      userId: 3,
      userImage: "https://picsum.photos/id/23/48",
      userName: "Emily Johnson",
      lastMessage: "Got it. Thanks!",
      lastMessageTime: "2025-01-24T20:15:00Z",
    },
  ]);
}
