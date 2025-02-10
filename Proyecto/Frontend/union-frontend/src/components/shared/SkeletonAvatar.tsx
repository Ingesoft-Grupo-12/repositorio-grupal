import Skeleton from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";

export default function ChatSkeleton() {
  return (
    <div className="flex items-center space-x-3 p-2 ">
      <Skeleton circle width={42} height={42} />
    </div>
  );
}
