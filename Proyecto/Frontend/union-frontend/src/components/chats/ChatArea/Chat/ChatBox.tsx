"use client";

import { useEffect, useState, useRef } from "react";
import { BsSend, BsEmojiSmile } from "react-icons/bs";
import { GoPaperclip } from "react-icons/go";
import EmojiPicker from "emoji-picker-react";
import { EmojiStyle } from "emoji-picker-react";
import { hiddenEmojis, categories } from "@/data/emojiPickerConfig";
import ClipMenu from "../ClipMenu";

type ChatBoxProps = {
  onSendMessage: () => void;
  input: string;
  setInput: React.Dispatch<React.SetStateAction<string>>;
};

export default function ChatBox({
  onSendMessage,
  input,
  setInput,
}: ChatBoxProps) {
  const [showPicker, setShowPicker] = useState(false);
  const [showClipMenu, setShowClipMenu] = useState(false);
  const [isPollModalOpen, setIsPollModalOpen] = useState(false);

  const inputRef = useRef<HTMLInputElement>(null);
  const emojiPickerRef = useRef<HTMLDivElement>(null);
  const clipMenuRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (
        emojiPickerRef.current &&
        !emojiPickerRef.current.contains(event.target as Node)
      ) {
        setShowPicker(false);
      }
      if (
        clipMenuRef.current &&
        !clipMenuRef.current.contains(event.target as Node) &&
        !isPollModalOpen
      ) {
        setShowClipMenu(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [isPollModalOpen]);

  const handleEmojiClick = (emojiObject: { emoji: string }) => {
    setInput((prev) => prev + emojiObject.emoji);
    if (inputRef.current) {
      inputRef.current.focus();
    }
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter" && !event.shiftKey) {
      event.preventDefault();
      onSendMessage();
    }
  };

  return (
    <div className="relative bg-gray-200 p-4 flex items-center mt-2 rounded-xl">
      <input
        ref={inputRef}
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyDown={handleKeyDown}
        placeholder="Escribe tu mensaje..."
        className="bg-transparent flex-grow p-2 rounded-lg focus:outline-none"
      />

      <div className="relative" ref={emojiPickerRef}>
        <BsEmojiSmile
          size={25}
          className="cursor-pointer mr-5"
          onClick={() => setShowPicker((prev) => !prev)}
        />
        <div
          className={`absolute bottom-12 right-0 z-10 transition-all duration-300 transform ${
            showPicker ? "opacity-100 scale-100" : "opacity-0 scale-95"
          }`}
          style={{ pointerEvents: showPicker ? "auto" : "none" }}
        >
          <EmojiPicker
            emojiStyle={EmojiStyle.NATIVE}
            searchPlaceholder={"Buscar emojis"}
            onEmojiClick={handleEmojiClick}
            hiddenEmojis={hiddenEmojis}
            previewConfig={{ showPreview: false }}
            categories={categories}
            searchDisabled={true}
          />
        </div>
      </div>

      <div className="relative" ref={clipMenuRef}>
        <GoPaperclip
          size={25}
          className="cursor-pointer mr-5"
          onClick={() => setShowClipMenu((prev) => !prev)}
        />
        {showClipMenu && <ClipMenu setIsPollModalOpen={setIsPollModalOpen} />}
      </div>

      <BsSend
        size={24}
        className="cursor-pointer mr-5"
        onClick={onSendMessage}
      />
    </div>
  );
}
