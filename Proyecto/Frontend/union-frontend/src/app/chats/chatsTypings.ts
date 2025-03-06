export type ChatDataType = {
  userId: number;
  userImage: string;
  userName: string;
  lastMessage: string;
  lastMessageTime: string;
};

export type MessageType = {
  messageId: number;
  senderId: number;
  content: string;
  time: string;
};

export type UserType = {
  id: string;
  userimage: string;
  username: string;
  email: string;
  role: string;
};

export type CourseMessageType = {
  id: number;
  senderId: string;
  courseId: number;
  senderImage: string;
  senderName: string;
  content: string;
  timestamp: string;
};

export type SelectedChatDataType = {
  userId: number;
  userImage: string;
  userName: string;
};

export type CourseType = {
  courseId: number;
  courseCode: string;
  courseName: string;
  courseDescription: string;
  courseUsers: Array<UserType>;
  lastMessageSenderName: string;
  lastMessageContent: string;
  lastMessageTime: string;
};

export type CourseDetailsType = {
  courseId: number;
  courseCode: string;
  name: string;
  description: string;
  teacherId: string;
  participants: Array<UserType>;
};

export type ModuleType = "messages" | "friends" | "courses";
