"use client";

import { useEffect, useState } from "react";
import ChatHeader from "./Chat/ChatHeader";
import ChatBody from "./Chat/ChatBody";
import ChatBox from "./Chat/ChatBox";
import CourseHeader from "./Course/CourseHeader";
import CourseBody from "./Course/CourseBody";
import {
  MessageType,
  SelectedChatDataType,
  CourseType,
  ModuleType,
  UserType,
  CourseMessageType,
} from "@/app/chats/chatsTypings";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

type ChatAreaProps = {
  selectedModule: ModuleType;
  selectedChat: SelectedChatDataType | null;
  selectedFriend: UserType | null;
  selectedCourse: CourseType | null;
};

export default function ChatArea({
  selectedModule,
  selectedChat,
  selectedFriend,
  selectedCourse,
}: ChatAreaProps) {
  const [chatMessages, setChatMessages] = useState<MessageType[]>([]);
  const [courseMessages, setCourseMessages] = useState<CourseMessageType[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [userId, setUserId] = useState<number | null>(null);
  const [stompClient, setStompClient] = useState<Client | null>(null);
  const [isConnected, setIsConnected] = useState(false);
  const [input, setInput] = useState("");

  useEffect(() => {
    const token = "";
    const socket = new SockJS("http://localhost:8080/union-backend/ws");
    const client = new Client({
      webSocketFactory: () => socket,
      connectHeaders: {
        Authorization: `Bearer ${token}`,
      },
      debug: (str: string) => console.log(str),
      reconnectDelay: 5000,
    });

    client.onConnect = () => {
      setIsConnected(true);
      // Suscripción al canal de mensajes del curso
      client.subscribe(
        `/topic/courses/${selectedCourse?.courseId}/chat`,
        (message) => {
          try {
            // Se espera que el mensaje sea un JSON con { name, content, timestamp }
            const parsed = JSON.parse(message.body);
            setCourseMessages((prev) => [...prev, parsed]);
          } catch (e) {
            console.error("Error al parsear el mensaje:", e);
          }
        }
      );
    };

    client.onStompError = (frame) => {
      console.error("Error:", frame.headers.message, frame.body);
    };

    client.activate();
    setStompClient(client);

    return () => {
      if (client && client.deactivate) {
        client.deactivate();
        setIsConnected(false);
      }
    };
  }, [selectedCourse?.courseId]);

  const sendMessage = () => {
    if (stompClient && stompClient.connected) {
      const token = "";
      const senderId = userId;
      // Se construye el payload incluyendo el contenido y el id del sender
      const payload = { content: input, senderId };
      stompClient.publish({
        destination: `/app/chat/${selectedCourse?.courseId}/send`,
        body: JSON.stringify(payload),
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      setInput("");
    }
  };

  useEffect(() => {
    fetchUserId();
  }, []);

  useEffect(() => {
    if (userId) {
      if (selectedChat) {
        fetchMessages();
      } else if (selectedCourse) {
        fetchCourseMessages();
      }
    }
  }, [selectedChat, selectedCourse, userId]);

  const fetchUserId = async () => {
    setLoading(true);
    try {
      const response = await fetch("http://localhost:3000/api/me");
      if (!response.ok) throw new Error("Error al obtener usuario");
      const data = await response.json();
      setUserId(data[0].userId);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const fetchMessages = async () => {
    setLoading(true);
    try {
      const response = await fetch("http://localhost:3000/api/messages");
      if (!response.ok) throw new Error("Error al cargar mensajes");
      const data: MessageType[] = await response.json();
      setChatMessages(data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const fetchCourseMessages = async () => {
    setLoading(true);
    try {
      const response = await fetch("http://localhost:3000/api/messages/course");
      if (!response.ok) throw new Error("Error al cargar mensajes");
      const data: CourseMessageType[] = await response.json();
      setCourseMessages(data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  if (!selectedChat && selectedModule === "messages") {
    return (
      <div className="flex flex-col ml-2 bg-white p-4 rounded-xl w-full min-w-96 h-full items-center justify-center">
        <p className="text-gray-500">
          Selecciona un chat para comenzar la conversación.
        </p>
      </div>
    );
  } else if (!selectedCourse && selectedModule === "courses") {
    return (
      <div className="flex flex-col ml-2 bg-white p-4 rounded-xl w-full min-w-96 h-full items-center justify-center">
        <p className="text-gray-500">
          Selecciona un curso para comenzar la conversación.
        </p>
      </div>
    );
  } else if (!selectedFriend && selectedModule === "friends") {
    return (
      <div className="flex flex-col ml-2 bg-white p-4 rounded-xl w-full min-w-96 h-full items-center justify-center">
        <p className="text-gray-500">
          ¡En la barra lateral izquierda puedes añadir amigos para chatear con
          ellos!
        </p>
      </div>
    );
  }

  if (selectedChat) {
    return (
      <div className="flex flex-col ml-2 bg-white p-4 rounded-xl w-full min-w-96 h-full">
        <ChatHeader
          userName={selectedChat.userName}
          userImage={selectedChat.userImage}
        />
        {loading ? (
          <p className="text-gray-500 text-center">Cargando mensajes...</p>
        ) : (
          <ChatBody chatMessages={chatMessages} userId={userId} />
        )}
        <div className="mt-auto">
          <ChatBox onSendMessage={sendMessage} />
        </div>
      </div>
    );
  } else if (selectedCourse) {
    return (
      <div className="flex flex-col ml-2 bg-white p-4 rounded-xl w-full min-w-96 h-full">
        <CourseHeader
          courseName={selectedCourse.courseName}
          courseImage={selectedCourse.courseImage}
          courseUsers={selectedCourse.courseUsers}
        />
        {loading ? (
          <p className="text-gray-500 text-center">Cargando mensajes...</p>
        ) : (
          <CourseBody courseMessages={courseMessages} userId={userId!} />
        )}
        <div className="mt-auto">
          <ChatBox onSendMessage={sendMessage} />
        </div>
      </div>
    );
  }
}
