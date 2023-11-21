package com.github.may2beez.farmhelperv2.util.helper;

import com.github.may2beez.farmhelperv2.handler.RotationHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.client.Minecraft;

import java.util.Optional;

@Getter
@Setter
public class RotationConfiguration {
    private final Minecraft mc = Minecraft.getMinecraft();
    private Rotation from;
    private Optional<Rotation> to = Optional.empty();
    private Optional<Target> target = Optional.empty();
    private long time;
    private Optional<Runnable> callback;
    @Setter
    @Getter
    @Accessors(fluent = true)
    private boolean goingBackToClientSide = false;
    @Getter
    @Setter
    @Accessors(fluent = true)
    private boolean followTarget = false;
    @Getter
    @Setter
    @Accessors(fluent = true)
    private boolean randomness = false;

    public enum RotationType {
        SERVER,
        CLIENT
    }
    private RotationType rotationType = RotationType.CLIENT;

    public RotationConfiguration(Rotation from, Rotation to, long time, RotationType rotationType, Runnable callback) {
        this.from = from;
        this.to = Optional.ofNullable(to);
        this.time = time;
        this.rotationType = rotationType;
        this.callback = Optional.ofNullable(callback);
    }

    public RotationConfiguration(Rotation from, Target target, long time, RotationType rotationType, Runnable callback) {
        this.from = from;
        this.time = time;
        this.target = Optional.ofNullable(target);
        this.rotationType = rotationType;
        this.callback = Optional.ofNullable(callback);
    }

    public RotationConfiguration(Rotation to, long time, Runnable callback) {
        this.from = RotationHandler.getInstance().getConfiguration() != null && RotationHandler.getInstance().getConfiguration().goingBackToClientSide() ? new Rotation(RotationHandler.getInstance().getServerSideYaw(), RotationHandler.getInstance().getServerSidePitch()) : new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        this.to = Optional.ofNullable(to);
        this.time = time;
        this.callback = Optional.ofNullable(callback);
    }

    public RotationConfiguration(Rotation to, long time, RotationType rotationType, Runnable callback) {
        this.from = RotationHandler.getInstance().getConfiguration() != null && RotationHandler.getInstance().getConfiguration().goingBackToClientSide() ? new Rotation(RotationHandler.getInstance().getServerSideYaw(), RotationHandler.getInstance().getServerSidePitch()) : new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        this.to = Optional.ofNullable(to);
        this.time = time;
        this.rotationType = rotationType;
        this.callback = Optional.ofNullable(callback);
    }

    public RotationConfiguration(Target target, long time, Runnable callback) {
        this.from = RotationHandler.getInstance().getConfiguration() != null && RotationHandler.getInstance().getConfiguration().goingBackToClientSide() ? new Rotation(RotationHandler.getInstance().getServerSideYaw(), RotationHandler.getInstance().getServerSidePitch()) : new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        this.time = time;
        this.target = Optional.ofNullable(target);
        this.callback = Optional.ofNullable(callback);
    }

    public RotationConfiguration(Target target, long time, RotationType rotationType, Runnable callback) {
        this.from = RotationHandler.getInstance().getConfiguration() != null && RotationHandler.getInstance().getConfiguration().goingBackToClientSide() ? new Rotation(RotationHandler.getInstance().getServerSideYaw(), RotationHandler.getInstance().getServerSidePitch()) : new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        this.time = time;
        this.target = Optional.ofNullable(target);
        this.rotationType = rotationType;
        this.callback = Optional.ofNullable(callback);
    }
}
