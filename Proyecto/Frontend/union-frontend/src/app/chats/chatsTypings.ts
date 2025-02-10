export type ChatDataType = {
  userId: number;
  userImage: string;
  userName: string;
  lastMessage: string;
  lastMessageTime: string;
};

export type FriendType = {
  userId: number;
  userImage: string;
  userName: string;
  email: string;
  requestStatus: string;
};

export type MessageType = {
  messageId: number;
  senderId: number;
  content: string;
  time: string;
};

export type SelectedChatDataType = {
  userId: number;
  userImage: string;
  userName: string;
};

export type ModuleType = "messages" | "friends" | "transport";
