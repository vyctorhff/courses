"use client"; // see state-comp.tsx

import { FC } from "react";

const cities = ['natal', 'recife', 'fortaleza'];

export const Event1:FC<{}> = () => {
    const handler = (city:string) => console.log(city);
    // const handler = (event: MouseEvent) => console.log(city);
    return (<ul>
        {cities.map((city) => (
            <li
                key={city}
                //onClick={handler} only on next is wrong
            >
                {city}
            </li>
        ))}
    </ul>);
};
