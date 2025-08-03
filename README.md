# 🐍 Procedural Generation

Welcome to my **procedural animation** sandbox!  
This project is an early experiment in bringing entities to life without hand-crafted frames.  
Here, a **snake-like being** slithers through space, guided by math.

---

## 🤔 What is Procedural Animation?

Procedural animation is motion **generated on the fly** through code.  
Unlike traditional animation, there are no pre-recorded frames. Instead, the motion emerges from rules, math, and input.

- ⏱️ Real-time character movement  
- 🌊 Fluid simulations  
- 🐍 A snake with no animation sprites—just segments obeying constraints

---

## ✨ Features Implemented So Far

| 📄 File              | 🔧 Role                                                                       |
|----------------------|--------------------------------------------------------------------------------|
| `Main.java`          | Initializes the simulation, sets up the frame and loop                        |
| `Entity.java`        | Base class with position, velocity, and angle logic                           |
| `Snake.java`         | The star of the show: controls segment-chain logic for procedural movement    |
| `Segment.java`       | Represents a body part of the snake; follows the segment ahead of it          |
| `KeyHandler.java`    | Handles keyboard input (WASD / arrows) to steer the snake                     |
| `MouseHandler.java`  | Handles mouse following                                                        |

---

## 🎮 Behavior and Motion

The snake moves with a **procedural wiggle** — each `Segment` smoothly follows the one before it.  
As you steer with the keyboard, the head pulls the body along, and the chain reacts in real-time.

This technique mimics simple **kinematics** to give the illusion of soft-bodied motion using only angles and distances — no physics engine, no bones, just code.

---

## 🌱 Future Features

- 🔁 Smoother, smarter turning  
- 🐕 More animals  
- 🧪 Debug UI for visualizing constraints and deltas
