package com.embabel.prepper.shell;

import com.embabel.agent.core.AgentPlatform;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
public record PrepperShell(AgentPlatform agentPlatform) {
}
