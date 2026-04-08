'use client';

import { createContext } from 'react';

/**
 * Context é o mesmo que useState porém é feito para ser compartililhado entre
 * componente e centralizar as alterações.
 */

export const ColorContext = createContext('color');

type RollTheDiceType = {
    value: number,
    callBack: (num: number) => void,
};
// o tipo é opicional
export const RollTheDice = createContext<RollTheDiceType>({value: 0, callBack: (num: number) => {}});