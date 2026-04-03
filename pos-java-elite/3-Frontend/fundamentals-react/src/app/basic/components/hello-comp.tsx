import { FC } from "react";

export const HelloComp = () => {
    return <p>Hello world componets</p>;
};

export const HelloName: FC<{ name: string}> = ({name}) => (
    <><p>Hello world to {name}</p></>
);