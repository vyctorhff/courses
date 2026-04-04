"use client";

import { FC, MouseEvent, useState } from "react";

/**
 * Next client vs server side
 * 
 * 
 * Server side
 *  é componente é executado do lado do servidor.
 *  não pode ter eventos, nem estados etc
 * 
 * Client side
 *  usado para interações que começam e terminam na navegador do cliente.
 */


/**
 * props vs state
 * 
 * props deve ser imutáveis
 * 
 * state deve ser mudado dentro do componente
 * 
 * re-render
 *   ambos quando alterados provocam um re-render do componentes
 */

const cities = ['natal', 'recife', 'fortaleza', 'maceio'];

export const State1:FC = () => {
    const [selectCityIndex, setSelectCityIndex] = useState(-1); // initial state: -1

    const getCityStyle = (index: number) => {
        return (selectCityIndex == index)
            ? { backgroundColor: 'red', width: '100px'}
            : { backgroundColor: 'white', width: '100px' };
    };

    const handler = (index: number, city: string) => {
        setSelectCityIndex(index);
        console.log(`${index}-${city}`)
    };

    return (<>
        <p>City chosen: {cities[selectCityIndex]}</p>
        <ul>
            {cities.map((city, index) => (
                <li
                    style={getCityStyle(index)}
                    key={city}
                    onClick={() => handler(index, city)}
                >
                    {city}
                </li>
            ))}
        </ul>
    </>);
};