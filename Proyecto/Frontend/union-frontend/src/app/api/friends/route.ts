import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json([
    {
      userId: 1,
      userImage: "https://picsum.photos/id/100/48",
      userName: "John Doe",
      userEmail: "jhondoe@unal.edu.co",
      requestStatus: "accepted",
    },
    {
      userId: 7,
      userImage: "https://picsum.photos/id/202/48",
      userName: "Pablo Gomez",
      userEmail: "pablogomez@unal.edu.co",
      requestStatus: "accepted",
    },
  ]);
}
