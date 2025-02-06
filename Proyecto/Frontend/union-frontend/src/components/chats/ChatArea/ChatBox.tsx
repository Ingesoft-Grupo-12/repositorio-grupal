"use client";

import { useState, useRef, useEffect } from "react";
import { BsSend, BsEmojiSmile } from "react-icons/bs";
import EmojiPicker, { Categories } from "emoji-picker-react";
import { EmojiStyle } from "emoji-picker-react";

export default function ChatBox() {
  const [message, setMessage] = useState("");
  const [showPicker, setShowPicker] = useState(false);
  const inputRef = useRef<HTMLInputElement>(null);
  const pickerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (
        pickerRef.current &&
        !pickerRef.current.contains(event.target as Node)
      ) {
        setShowPicker(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  const handleEmojiClick = (emojiObject: { emoji: string }) => {
    setMessage((prev) => prev + emojiObject.emoji);
    if (inputRef.current) {
      inputRef.current.focus();
    }
  };

  const hiddenEmojis = [
    "1fae0",
    "1f972",
    "1fae2",
    "1fae3",
    "1fae1",
    "1fae5",
    "1f978",
    "1fae4",
    "1f979",
    "1fab9",
    "1faba",
    "1fab1",
    "1f43b-200d-2744-fe0f",
  ];

  const categories = [
    {
      name: "Recientes",
      category: Categories.SUGGESTED,
    },
    {
      name: "Emoticonos y personas",
      category: Categories.SMILEYS_PEOPLE,
    },
    {
      name: "Animales y naturaleza",
      category: Categories.ANIMALS_NATURE,
    },
    {
      name: "Comidas y bebidas",
      category: Categories.FOOD_DRINK,
    },
    {
      name: "Viajes y lugares",
      category: Categories.TRAVEL_PLACES,
    },
    {
      name: "Actividades",
      category: Categories.ACTIVITIES,
    },
    {
      name: "Objetos",
      category: Categories.OBJECTS,
    },
    {
      name: "Simbolos",
      category: Categories.SYMBOLS,
    },
  ];

  return (
    <div className="relative bg-gray-200 p-4 flex items-center mt-2 rounded-xl">
      <input
        ref={inputRef}
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Escribe tu mensaje..."
        className="bg-transparent flex-grow p-2 rounded-lg focus:outline-none"
      />

      <div className="relative" ref={pickerRef}>
        <BsEmojiSmile
          size={24}
          className="cursor-pointer mr-5"
          onClick={() => setShowPicker((prev) => !prev)}
        />
        {showPicker && (
          <div className="absolute bottom-12 right-0 z-10">
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
        )}
      </div>

      <BsSend
        size={24}
        className="cursor-pointer mr-5"
        onClick={() => {
          console.log("Mensaje enviado:", message);
          setMessage("");
        }}
      />
    </div>
  );
}
