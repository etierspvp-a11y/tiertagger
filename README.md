# TierTagger

A Minecraft 1.21.1 Fabric mod that displays player tier rankings from the Xrawr tierlist in-game.

## Features

- 🎮 **Client-side mod** - Works on any server
- 📊 **Tier Display** - Shows player tiers in chat and player lists
- 🎨 **Color-coded** - Tiers are displayed with color-coded boxes matching Xrawr tierlist colors
- ⚡ **Cached** - Tier data is cached for 1 hour to reduce API calls
- 🔄 **Automatic** - Fetches tier data when players join

## Installation

1. Download the latest mod JAR from releases
2. Install [Fabric Loader](https://fabricmc.net/) for Minecraft 1.21.1
3. Place the JAR in your `.minecraft/mods` folder
4. Launch Minecraft

## How It Works

When a player joins the server:
1. The mod fetches their tier data from the Xrawr API
2. Displays their highest tier in the join message
3. Shows tiers in the player list and chat
4. Caches the data to avoid repeated API calls

## Tier Colors

- **HT1-HT3**: Yellow (#E8B23A)
- **HT4-HT5**: Purple (#B794F6)
- **LT1**: Yellow (#E8B23A)
- **LT2**: White (#FFFFFF)
- **LT3**: Orange (#FF9654)
- **LT4-LT5**: Purple (#B794F6)

## API

Uses the Xrawr tierlist API: `https://xrawrtl.netlify.app/api/search/{username}`

## Building

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/`

## License

MIT
