import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json([
    {
      courseId: 1,
      courseImage: "https://picsum.photos/id/87/48",
      courseName: "Ingesoft 1",
      courseUsers: [
        {
          userId: 1,
          userImage: "https://picsum.photos/id/100/48",
          userName: "John Doe",
          userEmail: "jhondoe@unal.edu.co",
          requestStatus: "accepted",
        },
        {
          userId: 2,
          userImage: "https://picsum.photos/id/202/48",
          userName: "Pablo Gomez",
          userEmail: "pablogomez@unal.edu.co",
          requestStatus: "accepted",
        },
        {
          userId: 3,
          userImage: "https://picsum.photos/id/23/48",
          userName: "Emily Johnson",
          userEmail: "emilyjhonson@unal.edu.co",
          requestStatus: "denied",
        },
        {
          userId: 4,
          userImage: "https://picsum.photos/id/38/48",
          userName: "Jane Smith",
          userEmail: "janesmith@unal.edu.co",
          requestStatus: "pending",
        },
      ],
      lastUserName: "Jane Smith",
      lastMessage: "Hello! How are you?",
      lastMessageTime: "2025-01-26T14:00:00Z",
    },
  ]);
}
