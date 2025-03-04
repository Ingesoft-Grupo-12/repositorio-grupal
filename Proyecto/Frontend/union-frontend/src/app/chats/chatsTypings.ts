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
  userId: number;
  userImage: string;
  userName: string;
  userEmail: string;
  requestStatus: string;
};

export type CourseMessageType = {
  messageId: number;
  user: UserType;
  content: string;
  time: string;
};

export type SelectedChatDataType = {
  userId: number;
  userImage: string;
  userName: string;
};

export type CourseType = {
  courseId: number;
  courseImage: string;
  courseName: string;
  courseUsers: Array<UserType>;
  lastUserName: string;
  lastMessage: string;
  lastMessageTime: string;
};

export type ModuleType = "messages" | "friends" | "courses";
