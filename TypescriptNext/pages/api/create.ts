import {prisma} from '../../lib/prisma'
import { NextApiRequest, NextApiResponse } from 'next'

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  const {note} = req.body

  try {
    await prisma.note.create(
      {data: {note}}
    )

    res.status(200).json({message: 'Note Created'})
  } catch (err) {
    console.log("failure", err)
  }
}