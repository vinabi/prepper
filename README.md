# Spring AI + Embabel Workshop Documentation
## Meeting Preparation Agent  
---

# 1. Introduction

This project implements an **AI-powered meeting preparation agent** using the **Embabel agent framework** built on top of **Spring AI**.

The goal of the system is to automatically generate a **meeting briefing** by orchestrating several AI-driven tasks.

Instead of writing a fixed workflow, the system allows an AI agent to dynamically determine the best sequence of actions needed to achieve the goal.

The agent performs the following tasks:

1. Research meeting participants  
2. Analyze relevant industry trends  
3. Develop a meeting strategy  
4. Generate a final meeting briefing

Each step is implemented as an **Embabel Action**, and the overall workflow is managed by the **Agent Platform**.

---

# 2. Agent Orchestration with Embabel

Embabel provides a framework for building **goal-driven AI agents**.

Traditional AI pipelines are typically static:

```

Step 1 → Step 2 → Step 3 → Step 4

```

Embabel instead follows a **dynamic planning model**:

```

Goal
↓
Planner
↓
Action execution
↓
Condition evaluation
↓
Replan if necessary

````

This allows the agent to adapt to new information during execution.

---

# 3. Core Concepts of Embabel

## Actions

Actions represent tasks an agent can perform.

Examples from this project:

- Research participants
- Analyze industry trends
- Create meeting strategy
- Generate meeting briefing

Example annotation:

```java
@Action
Domain.Participants researchParticipants(...)
````

Each action can use AI models, databases, or external tools.

---

## Goals

A goal represents the **desired outcome** of the agent.

In this project the goal is:

```
Produce a meeting briefing
```

Example annotation:

```java
@AchievesGoal(description = "Produce a briefing for the meeting")
```

When this goal is satisfied, the agent stops execution.

---

## Domain Model

The **domain model** represents structured data that flows between actions.

It defines the objects that describe the problem domain.

Examples:

| Model            | Purpose                       |
| ---------------- | ----------------------------- |
| Meeting          | Information about the meeting |
| Contact          | Participant details           |
| IndustryAnalysis | Industry insights             |
| MeetingStrategy  | Strategic discussion points   |
| Briefing         | Final generated report        |

Example domain record:

```java
public record Meeting(String context, String objective, List<String> participants) {}
```

These objects allow the agent to pass structured information between steps.

---

# 4. AI Model Integration

The system uses **AWS Bedrock** models for AI processing.

Configured models include:

| Model       | Purpose                |
| ----------- | ---------------------- |
| Nova Pro    | high-quality reasoning |
| Nova Lite   | balanced performance   |
| Titan Embed | text embeddings        |

Example configuration:

```properties
embabel.models.default-llm=us.amazon.nova-pro-v1:0
embabel.models.default-embedding-model=amazon.titan-embed-text-v2:0
```

These models power research, analysis, and document generation.

---

# 5. AI Personas

Each agent task is assigned a **persona** to guide the AI's behavior.

Personas define:

* role
* goal
* backstory

Example:

```properties
prepper.researcher.persona.role=Research Specialist
prepper.researcher.persona.goal=Conduct research on meeting participants
```

Defined personas include:

| Persona              | Responsibility          |
| -------------------- | ----------------------- |
| Research Specialist  | Research participants   |
| Industry Analyst     | Analyze market trends   |
| Strategy Advisor     | Create meeting strategy |
| Briefing Coordinator | Compile final briefing  |

Personas help produce more consistent AI outputs.

---

# 6. Data Persistence

Participant data is stored using **Spring Data JDBC** with an **H2 in-memory database**.

Example repository:

```java
interface ContactRepository extends ListCrudRepository<Contact, Long> {}
```

This allows the system to persist contact information discovered during research.

The database schema includes fields such as:

* name
* email
* description
* timestamps

---

# 7. Vector Search for Contact Resolution

To identify participants from natural language input, the system uses **vector similarity search**.

Steps involved:

1. Convert participant text into embeddings
2. Store embeddings in a vector store
3. Perform similarity search when resolving contacts

Example configuration:

```java
VectorStore vectorStore = SimpleVectorStore.builder(embeddingModel).build();
```

Benefits:

* Handles fuzzy name matching
* Improves participant identification
* Enables semantic search

---

# 8. Contact Service

The **ContactService** manages participant data.

Responsibilities include:

* retrieving contacts from the database
* resolving contacts using vector similarity
* creating new contacts from AI research

Example method:

```java
Optional<Contact> resolveContact(String identification)
```

If a contact does not exist, the system:

1. generates a new contact using AI
2. stores it in the database
3. adds it to the vector index

---

# 9. Agent Workflow

The agent executes the following sequence of actions.

### Step 1 — Research Participants

The agent identifies participants and gathers relevant information.

Example action:

```java
@Action
Domain.Participants researchParticipants(...)
```

---

### Step 2 — Industry Analysis

The agent analyzes industry trends relevant to the meeting context.

Example action:

```java
@Action
Domain.IndustryAnalysis analyzeIndustry(...)
```

---

### Step 3 — Meeting Strategy

The system generates talking points and strategic questions.

Example action:

```java
@Action
Domain.MeetingStrategy formulateMeetingStrategy(...)
```

---

### Step 4 — Generate Briefing

The final briefing combines all gathered information.

Example action:

```java
@Action
@AchievesGoal
Domain.Briefing produceBriefing(...)
```

This action produces the final output document.

---

# 10. Web Search Integration

To enhance research capabilities, the system integrates **Brave MCP Web Search** tools.

This allows the agent to access live web data.

Example configuration:

```properties
prepper.researcher.tool-groups=web
prepper.industry-analyzer.tool-groups=web
```

The tools are grouped under a **ToolGroup**:

```java
@Bean
ToolGroup mcpWebToolsGroup()
```

This enables the agent to perform:

* web searches
* online research
* information retrieval

---

# 11. CLI Interface

The application provides an interactive command-line interface using **Spring Shell**.

Example command:

```
prep
```

The user provides:

* meeting context
* meeting objective
* participant names

The system then invokes the agent to generate the briefing.

Example invocation:

```java
AgentInvocation.builder(agentPlatform)
```

---

# 12. System Architecture

The architecture consists of the following layers.

```
User CLI
   │
Agent Platform
   │
Agent Actions
   │
AI Models + Tools
   │
Database + Vector Store
```

Components:

| Component      | Role                        |
| -------------- | --------------------------- |
| AgentPlatform  | orchestrates agent workflow |
| PrepperAgent   | defines actions             |
| ContactService | manages participant data    |
| VectorStore    | handles semantic search     |
| AWS Bedrock    | powers AI reasoning         |

---

# 13. Key Benefits of the Approach

Using Embabel and Spring AI provides several advantages:

* dynamic agent orchestration
* modular AI actions
* structured domain modeling
* integration with external tools
* scalable AI workflows

This design makes it easier to build **complex AI agents with structured logic**.

---

# 14. Conclusion

This project demonstrates how modern AI frameworks can be used to build **intelligent automation systems**.

By combining:

* Spring Boot
* Spring AI
* Embabel agent orchestration
* AWS Bedrock models
* vector search
* web search tools

the system can automatically generate detailed meeting briefings with minimal user input.

This architecture can be extended to support other AI-driven workflows such as:

* research assistants
* business intelligence agents
* automated report generation
* decision support systems

Author: Nayab Irfan ([GitHub Repository](https://github.com/vinabi/prepper))

```
