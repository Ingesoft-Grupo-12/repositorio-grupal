import { getAccessToken } from "@auth0/nextjs-auth0";
import { NextResponse } from "next/server";

export async function GET() {
  try {
    const { accessToken } = await getAccessToken();
    console.log(accessToken)
    return NextResponse.json({ accessToken });
  } catch (error) {
    return NextResponse.json(
      { error: "No se pudo obtener el token" },
      { status: 401 }
    );
  }
}


