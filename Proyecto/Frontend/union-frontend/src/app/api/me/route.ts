import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json([
    {
      userId: 14,
      userImage:
        "https://lh3.googleusercontent.com/a/ACg8ocJjR0Iowwuy_T2YUARDOcmJZeMLwwtsfOd92NDHpH2CV88C0Q=s96-c",
      userName: "Sebastian",
      userEmail: "jhondoe@unal.edu.co",
    },
  ]);
}
