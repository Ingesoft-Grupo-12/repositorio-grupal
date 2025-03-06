import { NextResponse } from "next/server";
import { getAccessToken } from "@auth0/nextjs-auth0";

const API_URL = process.env.WS_BASE_URL;

export async function GET(
  request: Request,
  { params }: { params: { id: string } }
) {
  try {
    const { accessToken } = await getAccessToken();

    if (!accessToken) {
      return NextResponse.json({ error: "No autorizado" }, { status: 401 });
    }

    const courseId = params.id;

    const apiUrl = `${API_URL}/union-backend/api/courses/${courseId}`;

    const res = await fetch(apiUrl, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (!res.ok) {
      throw new Error(`Error en la API externa: ${res.statusText}`);
    }
    const courses = await res.json();

    return NextResponse.json(courses);
  } catch (error) {
    return NextResponse.json(
      { error: error instanceof Error ? error.message : "Unknown error" },
      { status: 500 }
    );
  }
}
