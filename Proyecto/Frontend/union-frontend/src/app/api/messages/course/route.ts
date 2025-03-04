import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json([
    {
      messageId: 1,
      user: {
        userId: 1,
        userImage: "https://picsum.photos/id/100/48",
        userName: "John Doe",
        userEmail: "jhondoe@unal.edu.co",
        requestStatus: "accepted",
      },
      content: "Hola",
      time: "2025-01-28 14:30:45",
    },
    {
      messageId: 2,
      user: {
        userId: 1,
        userImage: "https://picsum.photos/id/100/48",
        userName: "John Doe",
        userEmail: "jhondoe@unal.edu.co",
        requestStatus: "accepted",
      },
      content: "Waos",
      time: "2025-01-28 14:30:45",
    },
    {
      messageId: 3,
      user: {
        userId: 2,
        userImage: "https://picsum.photos/id/202/48",
        userName: "Pablo Gomez",
        userEmail: "pablogomez@unal.edu.co",
        requestStatus: "accepted",
      },
      content: "Como estas?",
      time: "2025-02-28 14:30:45",
    },
    {
      messageId: 4,
      user: {
        userId: 3,
        userImage: "https://picsum.photos/id/23/48",
        userName: "Emily Johnson",
        userEmail: "emilyjhonson@unal.edu.co",
        requestStatus: "denied",
      },
      content: "Bien",
      time: "2025-02-28 14:30:45",
    },
    {
      messageId: 5,
      user: {
        userId: 4,
        userImage: "https://picsum.photos/id/38/48",
        userName: "Jane Smith",
        userEmail: "janesmith@unal.edu.co",
        requestStatus: "pending",
      },
      content: "Como estas?",
      time: "2025-02-28 14:30:45",
    },
    {
      messageId: 6,
      user: {
        userId: 14,
        userImage: "https://picsum.photos/id/38/48",
        userName: "Jane Smith",
        userEmail: "janesmith@unal.edu.co",
        requestStatus: "pending",
      },
      content: "Como estas?",
      time: "2025-02-28 14:30:45",
    },
  ]);
}
