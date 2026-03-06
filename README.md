
---

# README.md

```markdown
# Meeting Prepper Agent

AI-powered meeting preparation agent built using **Spring AI**, **Embabel**, and **AWS Bedrock**.

This project demonstrates how to orchestrate multi-step AI workflows using domain-driven agent design.

---

# Features

- AI agent orchestration using Embabel
- AWS Bedrock Nova models
- Vector search for participant resolution
- Automatic meeting briefing generation
- Web search integration via MCP
- CLI interface using Spring Shell
- Contact database with H2
- Spring Boot backend

---

# Architecture

The agent performs the following workflow:

1. Research meeting participants
2. Analyze industry trends
3. Generate meeting strategy
4. Produce final briefing

Each step is implemented as an Embabel **Action** and dynamically orchestrated by the agent.

---

# Technology Stack

- Java 25
- Spring Boot
- Spring AI
- Embabel Agent Framework
- AWS Bedrock
- Nova Pro / Nova Lite models
- Titan Embeddings
- H2 database
- Vector search
- MCP Web Search
- Spring Shell

---

# Running the Project
./mvnw spring-boot:run


This launches the Embabel interactive shell.

---

# Available Commands

| Command | Description |
|------|------|
models | list available AI models |
agents | list registered agents |
tools | list available tools |
contacts | list saved contacts |
findcontact | search for a contact |
prep | run meeting preparation agent |

---

# Example Usage

Run:
prep

Provide:
meeting context
meeting objective
participants


The agent will generate a **meeting briefing document**.

---

# Project Structure
src/main/java/com/embabel/prepper
├ agent
│ ├ Domain.java
│ ├ ContactService.java
│ ├ PrepperAgent.java
│ └ PrepperConfig.java
├ shell
│ └ PrepperShell.java
└ PrepperApplication.java


Resources:


src/main/resources
├ application.properties
├ schema.sql
└ models/additional-bedrock.yaml


---

# Database

The application uses **H2 in-memory database** for storing contact information.

Contacts are automatically created when the agent researches new meeting participants.

---

# Vector Search

Participant data is embedded using **Titan embeddings** and stored in a vector store for similarity search.

This allows the agent to resolve participants from natural language input.

---

# License

Educational project created for the Spring AI + Embabel workshop @aws.

Start the application:
