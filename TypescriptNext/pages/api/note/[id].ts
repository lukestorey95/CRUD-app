import {prisma} from '../../../lib/prisma'
import { NextApiRequest, NextApiResponse } from 'next'

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  const noteId = req.query.id

  if (req.method === 'DELETE') {
    const note = await prisma.note.delete({
      where: {id: Number(noteId)},
    })
    res.json(note)
  } else if (req.method === "PUT") {
    const updatedNote = await prisma.note.update({
      where: {
        id: Number(noteId)
      },
      data: {
        note: String(req.body.note)
      }
    })
    res.json(updatedNote)
  }
  
  else {
    console.log("Invalid method: " + req.method)
  }
}